package uk.ac.tees.e4109732.mam_pong

import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.graphics.use

class GameScreen(val game: Main) : KtxScreen {
    private val paddleRegion = game.atlas?.findRegion("Paddle")

    override fun render(delta: Float) {
        clearScreen(0.12f, 0.12f, 0.12f)

        game.viewport.apply()
        game.batch.projectionMatrix = game.viewport.camera.combined
        game.batch.use { batch ->
            paddleRegion?.let { region ->
                for (i in 0 until game.viewport.worldWidth.toInt()) {
                    batch.draw(
                        region,
                        i.toFloat(),
                        game.viewport.worldHeight * 0.5f - 0.5f,
                        0.5f,
                        0.5f)
                }
            }
        }
    }

    override fun resize(width: Int, height: Int) {
        game.viewport.update(width, height, true)
    }
}
