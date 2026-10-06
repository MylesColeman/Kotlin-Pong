package uk.ac.tees.e4109732.mam_pong

import com.badlogic.gdx.graphics.Color

object Constants {
    val BACKGROUND_COLOR: Color = Color.valueOf("#333333")
    val CLEAR_COLOR: Color = Color.valueOf("#999999")

    const val WORLD_WIDTH = 20
    const val WORLD_HEIGHT = 40

    const val BALL_SPEED = 22

    const val PADDLE_WIDTH = 2.5f
    val PADDLE_COLOR: Color = Color.GREEN
    const val PADDLE_LEFT_ANGLE = 130f
    const val PADDLE_CENTER_ANGLE = 90f
    const val PADDLE_RIGHT_ANGLE = 50f
    const val PADDLE_DELTA_ANGLE = 15f
    const val PADDLE_SPEED = 5f
    const val ACCELEROMETER_SENSITIVITY = 10f

    val AI_PADDLE_COLOR: Color = Color.RED
    const val AI_PADDLE_SPEED = 8f
}
