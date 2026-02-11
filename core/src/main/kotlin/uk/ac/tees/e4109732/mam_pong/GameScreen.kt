package uk.ac.tees.e4109732.mam_pong

import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.graphics.use

class GameScreen(val game: Main) : KtxScreen {
    override fun render(delta: Float) {
        clearScreen(0.12f, 0.12f, 0.12f)

        game.viewport.apply()
        game.batch.use { batch ->
            batch.projectionMatrix = game.viewport.camera.combined
        }
    }

    override fun resize(width: Int, height: Int) {
        game.viewport.update(width, height, true)
    }
}
