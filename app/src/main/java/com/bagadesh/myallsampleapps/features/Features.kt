package com.bagadesh.myallsampleapps.features

/**
 * Created by bagadesh on 19/11/22.
 */
enum class Features(val displayTitle: String) {

    HOME("home"),
    EXPAND_CHANGE_ENVIRONMENT("Expand Change environment"),
    RANDOM_CANVAS("Sample Canvas Things"),
    NAV_CONTROLLER_FRAGMENT_TRANSACTION("NavController Fragment Animation"),
    PAGING3("Paging3"),
    CACHE_UI("CacheUI"),
    COMPOSE_INTERNALS("Compose Internals"),
    FLOW_EXAMPLES("Flow Examples"),
    TIME_LINE_MARKER("Time Line Marker"),
    COROUTINE_EXCEPTION_HANDLER("Coroutine Exception Handler"),
    LazyVerticalGridUI("Lazy Vertical Grid"),
    RxJavaSampleUI("Rx Java Sample"),
    RxJavaAndFlowUI("Rx Java with FLow"),
    GraphGrid("Graph Grid"),
    SwipeToShow("Swipe to show"),
    FeatureFlagUI("Feature Flag UI"),
    CounterTimerSyncUi("Counter Timer with Sync"),
    AutoFillExample("Auto Fill examples"),
    CollapsingToolbar("CollapsingToolbar"),
    BottomNavigation("BottomNavigation"),
    Epg("EpgScreen"),
    KotlinChannels("KotlinChannels"),
    LazyLazyColumn("LazyColumn"),
    ;

    companion object {
        val default = LazyLazyColumn
        ;

    }
}

