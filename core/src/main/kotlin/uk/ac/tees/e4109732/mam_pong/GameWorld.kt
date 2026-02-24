package uk.ac.tees.e4109732.mam_pong

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.badlogic.gdx.utils.viewport.Viewport

class GameWorld(gameScreen: GameScreen) {
    private val viewport: Viewport = gameScreen.game.viewport
    private val paddleTex: AtlasRegion? = gameScreen.game.atlas?.findRegion("Paddle")
    private val ballTex: AtlasRegion? = gameScreen.game.atlas?.findRegion("Ball")

    private val ball = Ball(viewport, ballTex)
    private val paddle = Paddle(viewport, paddleTex)

    fun render(batch: SpriteBatch, delta: Float) {
        ball.draw(batch, delta)
        paddle.draw(batch, delta)
        paddle.hitTest(ball)
    }

    fun reset() {
        ball.reset()
        paddle.reset()
    }
}
