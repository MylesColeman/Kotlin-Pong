package uk.ac.tees.e4109732.mam_pong

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.graphics.use
import ktx.scene2d.*

class GameScreen(val game: Main) : KtxScreen {
    private val paddleRegion = game.atlas?.findRegion("Paddle")

    private val stage = Stage(game.viewport, game.batch)
    private val aiScoreLabel: Label
    private val playerScoreLabel: Label

    init {
        val labelStyle = Label.LabelStyle(game.scoreFont, Color.WHITE)

        val root = scene2d.table {
            setFillParent(true)

            add(scene2d.table {
                setRound(false)
                top().left()
                aiScoreLabel = Label("0", labelStyle)
                add(aiScoreLabel).padLeft(0.4f).padTop(10.6f)
            }).height(12f).width(20f).expand().fill().row()

            add(scene2d.table {
                setRound(false)
                top().left()
                playerScoreLabel = Label("0", labelStyle)
                add(playerScoreLabel).padLeft(0.4f).padTop(1.55f)
            }).height(12f).width(20f).expand().fill()
        }
        stage.addActor(root)
    }

    override fun render(delta: Float) {
        clearScreen(0.12f, 0.12f, 0.12f)

        game.viewport.apply()
        game.batch.projectionMatrix = game.viewport.camera.combined
        game.batch.use { batch ->
            paddleRegion?.let { region ->
                val xOffset = 0.25f
                val lineThickness = 0.5f
                val yPos = (game.viewport.worldHeight * 0.5f) - (lineThickness * 0.5f)
                for (i in 0 until game.viewport.worldWidth.toInt()) {
                    batch.draw(
                        region,
                        i.toFloat() + xOffset, yPos,
                        0.5f, lineThickness)
                }
            }
        }

        stage.act(delta)
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        game.viewport.update(width, height, true)
    }

    override fun dispose() {
        stage.dispose()
    }
}
