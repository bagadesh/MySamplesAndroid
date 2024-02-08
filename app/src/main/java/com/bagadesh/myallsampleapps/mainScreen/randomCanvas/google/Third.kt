package com.bagadesh.myallsampleapps.mainScreen.randomCanvas.google

/**
 * Created by bagadesh on 03/12/22.
 */


import androidx.compose.runtime.Composable

@Composable
fun NestedSceneHostDemo() {
//    SceneHost {
//        Box(Modifier.padding(top = 100.dp).fillMaxSize()
//            .intermediateLayout { measurable, constraints, _ ->
//                println("SPEC, actually measure parent")
//                val placeable = measurable.measure(constraints)
//                layout(placeable.width, placeable.height) {
//                    println("SPEC, actually place parent")
//                    placeable.place(0, 0)
//                }
//            }) {
//            SceneHost {
//                Column {
//                    Box(
//                        Modifier.size(100.dp).background(Color.Red)
//                            .intermediateLayout { measurable, constraints, _ ->
//                                println("SPEC, actually measure child")
//                                val placeable = measurable.measure(constraints)
//                                layout(placeable.width, placeable.height) {
//                                    println("SPEC, actually place child")
//                                    placeable.place(0, 0)
//                                }
//                            })
//                    Box(
//                        Modifier.size(100.dp).background(Color.Green)
//                    )
//                }
//            }
//        }
//    }
}