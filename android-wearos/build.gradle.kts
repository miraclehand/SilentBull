plugins {
    id("com.android.application") version "8.1.1" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
    kotlin("android") version "2.0.0" apply false
}

tasks.register("clean", Delete::class) {
    delete(layout.buildDirectory)
}
