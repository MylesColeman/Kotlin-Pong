package uk.ac.tees.e4109732.mam_pong

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport

class Paddle(private val viewport: Viewport, private val texture: AtlasRegion?) {
    private val touchCoords = Vector2()
    private var centreX = 0f
    private var leftX = 0f
    private var y = 0.2f

    init {
        reset()
    }

    fun draw(batch: SpriteBatch, delta: Float) {
        touchCoords.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
        viewport.unproject(touchCoords)

        centreX += (touchCoords.x - centreX) * delta * Constants.PADDLE_SPEED
        leftX = centreX - Constants.PADDLE_WIDTH * 0.5f
        if (leftX < 0f) leftX = 0f
        else if (leftX > viewport.worldWidth - Constants.PADDLE_WIDTH)
            leftX = viewport.worldWidth - Constants.PADDLE_WIDTH
        centreX = leftX + Constants.PADDLE_WIDTH * 0.5f

        texture?.let {
            batch.setColor(Constants.PADDLE_COLOR)
            batch.draw(it, leftX, y, Constants.PADDLE_WIDTH, 1f)
            batch.setColor(1f, 1f, 1f, 1f)
        }
    }

    fun reset() {
        y = 0.2f
    }
}
