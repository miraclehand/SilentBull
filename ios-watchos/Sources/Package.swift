// swift-tools-version: 5.7
import PackageDescription

let package = Package(
    name: "SilentBull",
    platforms: [
        .iOS(.v15),
        .watchOS(.v8)
    ],
    products: [
        .library(
            name: "SilentBull",
            targets: ["SilentBull"]
        ),
    ],
    dependencies: [
        // 추후 필요시 의존성 추가
    ],
    targets: [
        .target(
            name: "SilentBull",
            dependencies: [],
            path: "Sources"
        ),
        .testTarget(
            name: "SilentBullTests",
            dependencies: ["SilentBull"],
            path: "Tests"
        ),
    ]
)
