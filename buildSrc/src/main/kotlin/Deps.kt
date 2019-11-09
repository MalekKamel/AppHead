
object Versions {
    const val androidx_appCompat = "1.1.0"
    const val materialDesign = "1.0.0-rc01"
    const val picasso = "2.71828"
    const val androidx_constraintLayout = "2.0.0-beta3"
    const val test_espressoCore = "3.2.0"
    const val androidx_junit = "1.1.1"
    const val androidx_coreKtx = "1.1.0"

    const val test_junit = "4.12"
    const val test_jUnitParams = "1.1.1"
    const val test_kotlinTestJunit5 = "3.4.0"
    const val test_kotlinTestExtKoin = "3.4.0"
}

object Deps {
    const val androidx_appCompat = "androidx.appcompat:appcompat:${Versions.androidx_appCompat}"
    const val androidx_coreKtx = "androidx.core:core-ktx:${Versions.androidx_coreKtx}"
    const val androidx_constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintLayout}"
    const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
    const val picasso  = "com.squareup.picasso:picasso:${Versions.picasso}"
}

object TestDeps {
    const val androidx_junit = "androidx.test.ext:junit:${Versions.androidx_junit}"
    const val test_junit = "junit:junit:${Versions.test_junit}"
    const val test_espressoCore = "androidx.test.espresso:espresso-core:${Versions.test_espressoCore}"
    const val test_jUnitParams = "pl.pragmatists:JUnitParams:${Versions.test_jUnitParams}"
    const val test_kotlinTestJunit5 = "io.kotlintest:kotlintest-runner-junit5:${Versions.test_kotlinTestJunit5}"
    const val test_kotlinTestExtKoin = "io.kotlintest:kotlintest-extensions-koin:${Versions.test_kotlinTestExtKoin}"
}