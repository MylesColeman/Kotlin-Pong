package uk.ac.tees.e4109732.mam_pong

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.assets.disposeSafely

class Main : KtxGame<KtxScreen>() {
    val batch by lazy { SpriteBatch() }
    val viewport = FitViewport(20f, 24f)

    val font by lazy { BitmapFont() }
    val whitePixel by lazy {
        val pixmap = Pixmap(1, 1, Pixmap.Format.RGBA8888)
        pixmap.setColor(Color.WHITE)
        pixmap.fill()
        val tex = Texture(pixmap)
        pixmap.dispose()
        tex
    }

    override fun create() {
        font.data.setScale(0.09f)
        font.setUseIntegerPositions(false)

        addScreen(MenuScreen(this))
        setScreen<MenuScreen>()
    }

    override fun dispose() {
        batch.disposeSafely()
        font.disposeSafely()
        whitePixel.disposeSafely()
        super.dispose()
    }
}
