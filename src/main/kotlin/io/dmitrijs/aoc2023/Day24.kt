package io.dmitrijs.aoc2023

import com.microsoft.z3.Context
import com.microsoft.z3.IntNum
import com.microsoft.z3.RatNum
import com.microsoft.z3.Status
import kotlin.math.roundToLong

class Day24(input: List<String>) {
    private val stones = input.map { line ->
        val (pos, vel) = line.split(" @ ")
        Stone(Point3d.of(pos), Point3d.of(vel))
    }

    fun puzzle1(range: LongRange): Int {
        var result = 0

        for (i in 0..<stones.lastIndex) for (j in (i + 1)..<stones.size) {
            val s1 = stones[i]
            val s2 = stones[j]
            s1.intersection(s2)?.takeIf { it in range }?.let { result++ }
        }

        return result
    }

    /**
     * Solved with Z3 - https://github.com/Z3Prover/z3.
     *
     * Previous iterations:
     *   - Example manually solved here - https://quickmath.com/webMathematica3/quickmath/equations/solve/advanced.jsp
     *     Result: (24, 13, 10) to (-3, 1, 2) = 47
     *   - Problem initially solved with Python & sympy
     *     Result: (191537613659010, 238270932096689, 137106090006865) to (206, 70, 247)
     */
    fun puzzle2(): Long {
        // rSx - start X position for rock
        // rSy - start Y position for rock
        // rSz - start Z position for rock
        // rVx - X velocity for rock
        // rVy - Y velocity for rock
        // rVz - Z velocity for rock

        // s<i>Sx - start X position for stone i
        // s<i>Sy - start Y position for stone i
        // s<i>Sz - start Z position for stone i
        // s<i>Vx - X velocity for stone i
        // s<i>Vy - Y velocity for stone i
        // s<i>Vz - Z velocity for stone i

        // To solve:
        //
        // (rSx - s1Sx) / (s1Vx - rVx) = (rSy - s1Sy) / (s1Vy - rVy) = (rSz - s1Sz) / (s1Vz - rVz)
        // (rSx - s2Sx) / (s2Vx - rVx) = (rSy - s2Sy) / (s2Vy - rVy) = (rSz - s2Sz) / (s2Vz - rVz)
        // (rSx - s3Sx) / (s3Vx - rVx) = (rSy - s3Sy) / (s3Vy - rVy) = (rSz - s3Sz) / (s3Vz - rVz)
        //
        // (rSx - s1Sx) * (s1Vy - rVy) - (rSy - s1Sy) * (s1Vx - rVx) = 0
        // (rSy - s1Sy) * (s1Vz - rVz) - (rSz - s1Sz) * (s1Vy - rVy) = 0
        // (rSx - s2Sx) * (s2Vy - rVy) - (rSy - s2Sy) * (s2Vx - rVx) = 0
        // (rSy - s2Sy) * (s2Vz - rVz) - (rSz - s2Sz) * (s2Vy - rVy) = 0
        // (rSx - s3Sx) * (s3Vy - rVy) - (rSy - s3Sy) * (s3Vx - rVx) = 0
        // (rSy - s3Sy) * (s3Vz - rVz) - (rSz - s3Sz) * (s3Vy - rVy) = 0

        val ctx = Context()
        val solver = ctx.mkSolver()
        val consts = listOf("rSx", "rSy", "rSz", "rVx", "rVy", "rVz")
        val symbols = consts.associateWith { sym -> ctx.mkRealConst(ctx.mkSymbol(sym)) }

        // Z3 works reliably with 4+ stones,
        // while sympy gives proper result with 3.
        stones.take(5).forEach { stone ->
            val (sx, sy, sz) = stone.pos
            val (vx, vy, vz) = stone.vel

            solver.add(
                ctx.mkEq(
                    ctx.mkMul(
                        ctx.mkSub(symbols["rSx"]!!, ctx.mkInt(sx)),
                        ctx.mkSub(ctx.mkInt(vy), symbols["rVy"]!!),
                    ),
                    ctx.mkMul(
                        ctx.mkSub(symbols["rSy"]!!, ctx.mkInt(sy)),
                        ctx.mkSub(ctx.mkInt(vx), symbols["rVx"]!!),
                    ),
                ),
                ctx.mkEq(
                    ctx.mkMul(
                        ctx.mkSub(symbols["rSy"]!!, ctx.mkInt(sy)),
                        ctx.mkSub(ctx.mkInt(vz), symbols["rVz"]!!),
                    ),
                    ctx.mkMul(
                        ctx.mkSub(symbols["rSz"]!!, ctx.mkInt(sz)),
                        ctx.mkSub(ctx.mkInt(vy), symbols["rVy"]!!),
                    ),
                ),
            )

            // For sympy
            val expr = """
                (rSx - $sx) * ($vy - rVy) - (rSy - $sy) * ($vx - rVx) = 0
                (rSy - $sy) * ($vz - rVz) - (rSz - $sz) * ($vy - rVy) = 0
            """.trimIndent()
            println(expr)
        }

        require(solver.check() == Status.SATISFIABLE) { "Solver expressions are not satisfiable." }

        val answers = consts.map { sym ->
            when (val res = solver.model.getConstInterp(symbols[sym]!!)) {
                is IntNum -> res.int64
                is RatNum -> res.toString().toDouble().roundToLong()
                else -> error("Unsupported result for '$sym' symbol.")
            }
        }

        return answers.subList(0, 3).sum()
    }

    private operator fun LongRange.contains(point: Pair<Double, Double>) = with(point) {
        first >= start && first <= endInclusive && second >= start && second <= endInclusive
    }

    private data class Stone(val pos: Point3d, val vel: Point3d) {
        // (Px + t * Vy; Py + t * Vy) -> ax + by = c
        // Parametric form -> standard form, where:
        //   a = Vy
        //   b = -Vx
        //   c = Vy * x - Vx * y
        private val line = Line(
            a = vel.y.toDouble(),
            b = -vel.x.toDouble(),
            c = pos.x.toDouble() * vel.y - pos.y.toDouble() * vel.x
        )

        fun intersection(other: Stone): Pair<Double, Double>? {
            if (line.parallelTo(other.line)) return null

            val (a1, b1, c1) = line
            val (a2, b2, c2) = other.line

            val x = (c1 * b2 - c2 * b1) / (a1 * b2 - a2 * b1)
            val y = (c2 * a1 - c1 * a2) / (a1 * b2 - a2 * b1)

            // (x - Px) same sign as Vx
            // (y - Py) same sign as Vy
            return (x to y)
                .takeIf { (x, _) -> (x > pos.x == vel.x > 0) && (x > other.pos.x == other.vel.x > 0) }
                ?.takeIf { (_, y) -> (y > pos.y == vel.y > 0) && (y > other.pos.y == other.vel.y > 0) }
        }

        private data class Line(val a: Double, val b: Double, val c: Double) {
            fun parallelTo(other: Line) = a * other.b == other.a * b
        }
    }

    private data class Point3d(val x: Long, val y: Long, val z: Long) {
        companion object {
            fun of(input: String) =
                input.split(',').map(String::trim).let { (x, y, z) -> Point3d(x.toLong(), y.toLong(), z.toLong()) }
        }
    }
}
