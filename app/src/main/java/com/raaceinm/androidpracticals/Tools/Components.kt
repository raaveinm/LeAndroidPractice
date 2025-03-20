package com.raaceinm.androidpracticals.Tools

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



fun  setGPUDataSet(item: String) {
    GPUData = GPUData.toMutableList().apply {
        add(GPUs(item))
    }
}

fun deleteGPUDataSet(item: String) {
    GPUData = GPUData.toMutableList().apply {
        remove(GPUs(item))
    }
}

var CPUData: Array<String> = arrayOf(
    "AMD Ryzen 9 7950X3D",
    "AMD Ryzen 9 7900X",
    "AMD Ryzen 9 7900",
    "AMD Ryzen 7 7800X3D",
    "AMD Ryzen 7 7700X",
    "AMD Ryzen 5 7600X",
    "AMD Ryzen 9 5900X",
    "AMD Ryzen 7 5800X",
    "AMD Ryzen 5 5600X",
)

var CPUImage: Array<String> = arrayOf(
    "https://www.amd.com/content/dam/amd/en/images/products/processors/ryzen/2505503-ryzen-9-7900x3d.jpg",
    "https://www.amd.com/content/dam/amd/en/images/products/processors/ryzen/2505503-ryzen-9-7900x.jpg",
    "https://www.amd.com/content/dam/amd/en/images/products/processors/ryzen/2505503-ryzen-9-7900x.jpg",
    "https://www.amd.com/content/dam/amd/en/images/products/processors/ryzen/2505503-ryzen-7-7800x3d.jpg",
    "https://www.amd.com/content/dam/amd/en/images/products/processors/ryzen/2505503-ryzen-7-7700x.jpg",
    "https://www.amd.com/content/dam/amd/en/images/products/processors/ryzen/2505503-ryzen-5-7600x.jpg",
    "https://www.amd.com/content/dam/amd/en/images/products/processors/ryzen/2505503-ryzen-9-5900x.jpg",
    "https://www.amd.com/content/dam/amd/en/images/products/processors/ryzen/2505503-ryzen-7-5800x.jpg",
    "https://www.amd.com/content/dam/amd/en/images/products/processors/ryzen/2505503-ryzen-5-5600x.jpg")

fun getCPUDataSet() = CPUData
fun getCPUImageDataSet() = CPUImage
fun getGPUDataSet() = GPUData

data class CPUItem(val name: String, val imageUrl: String)
