package com.raaceinm.androidpracticals.Tools

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringArrayResource

data class GPUs(var name: String)

var GPUData: List<GPUs> = mutableListOf(
    GPUs("NVIDIA GeForce RTX 4090"),
    GPUs("NVIDIA GeForce RTX 4080"),
    GPUs("NVIDIA GeForce RTX 4070 Ti"),
    GPUs("NVIDIA GeForce RTX 4070"),
    GPUs("NVIDIA GeForce RTX 3090 Ti"),
    GPUs("NVIDIA GeForce RTX 3080 Ti"),
    GPUs("NVIDIA GeForce RTX 3070 Ti"),
    GPUs("NVIDIA GeForce RTX 3060 Ti"),
    GPUs("NVIDIA GeForce GTX 1660 Super"),
    GPUs("NVIDIA GeForce GTX 1650 Super"),
    GPUs("AMD Radeon RX 7900 XTX"),
    GPUs("AMD Radeon RX 7900 XT"),
    GPUs("AMD Radeon RX 6950 XT"),
    GPUs("AMD Radeon RX 6800 XT"),
    GPUs("AMD Radeon RX Vega 64")
)

fun getGPUDataSet(): List<GPUs> {
    return GPUData
}

fun  setGPUDataSet(item: List<GPUs>) {
    GPUData = GPUData.toMutableList().also { it.addAll(item) }
}

data class CPUItem(val name: String, val imageUrl: String)
