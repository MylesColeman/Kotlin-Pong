package uk.ac.tees.e4109732.mam_pong

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.graphics.use

class MenuScreen(val game: Main) : KtxScreen {
    private val titleRegion = game.atlas?.findRegion("GameTitle")

    private val playButton = Rectangle(7f, 8f, 6f, 3f)
    private val touchPoint = Vector2()
    private val layout = GlyphLayout()
    private var textX = 0f
    private var textY = 0f

    override fun show() {
        layout.setText(game.font, "PLAY")
        textX = playButton.x + (playButton.width - layout.width) / 2
        textY = playButton.y + (playButton.height + layout.height) / 2
    }

    override fun render(delta: Float) {
        clearScreen(0.2f, 0.2f, 0.2f)

        game.viewport.apply()
        game.batch.projectionMatrix = game.viewport.camera.combined
        game.batch.use { batch ->
            titleRegion?.let { region ->
                batch.draw(region, 5f, 15f, 10f, 4f)
            }

            val thickness = 0.2f
            batch.draw(game.whitePixel, playButton.x, playButton.y, playButton.width, thickness)
            batch.draw(game.whitePixel, playButton.x, playButton.y + playButton.height - thickness, playButton.width, thickness)
            batch.draw(game.whitePixel, playButton.x, playButton.y, thickness, playButton.height)
            batch.draw(game.whitePixel, playButton.x + playButton.width - thickness, playButton.y, thickness, playButton.height)

            game.font.color = Color.WHITE
            game.font.draw(batch, layout, textX, textY)
        }

        if (Gdx.input.justTouched()) {
            game.viewport.unproject(touchPoint.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()))

            if (playButton.contains(touchPoint.x, touchPoint.y)) {
                if (!game.containsScreen<GameScreen>()) {
                    game.addScreen(GameScreen(game))
                }
                game.setScreen<GameScreen>()
            }
        }
    }

    override fun resize(width: Int, height: Int) {
        game.viewport.update(width, height, true)
        show()
    }
}
