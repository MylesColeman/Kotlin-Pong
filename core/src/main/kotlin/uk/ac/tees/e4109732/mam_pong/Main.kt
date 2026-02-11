package uk.ac.tees.e4109732.mam_pong

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.utils.viewport.FitViewport
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.assets.disposeSafely
import ktx.assets.toInternalFile
import ktx.scene2d.Scene2DSkin
import com.badlogic.gdx.scenes.scene2d.ui.Skin

class Main : KtxGame<KtxScreen>() {
    val batch by lazy { SpriteBatch() }
    val viewport = FitViewport(20f, 24f)
    val assetManager = AssetManager()

    var atlas: TextureAtlas? = null

    val font by lazy { BitmapFont() }
    val whitePixel by lazy {
        val pixmap = Pixmap(1, 1, Pixmap.Format.RGBA8888)
        pixmap.setColor(Color.WHITE)
        pixmap.fill()
        val tex = Texture(pixmap)
        pixmap.dispose()
        tex
    }

    lateinit var scoreFont: BitmapFont

    override fun create() {
        val generator = FreeTypeFontGenerator("gamefont.ttf".toInternalFile())
        val parameter = FreeTypeFontGenerator.FreeTypeFontParameter().apply {
            size = 48
            color = Color.WHITE
            minFilter = Texture.TextureFilter.Linear
            magFilter = Texture.TextureFilter.Linear
        }
        scoreFont = generator.generateFont(parameter)
        scoreFont.data.ascent = scoreFont.capHeight
        scoreFont.data.descent = 0f
        generator.dispose()

        scoreFont.data.setScale(0.05f)
        scoreFont.setUseIntegerPositions(false)

        font.data.setScale(0.09f)
        font.setUseIntegerPositions(false)

        Scene2DSkin.defaultSkin = Skin()

        addScreen(LoadingScreen(this))
        setScreen<LoadingScreen>()
    }

    override fun dispose() {
        batch.disposeSafely()
        font.disposeSafely()
        whitePixel.disposeSafely()
        assetManager.disposeSafely()
        atlas?.disposeSafely()
        scoreFont.disposeSafely()
        super.dispose()
    }
}
