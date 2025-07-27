# Navigation

Navigation is a lightweight, Kotlin-based navigation library for Compose Multiplatform projects, developed under the Atlas-KT organization. It provides an intuitive API for managing screen stacks and navigation within your application.

---

## Features

- **Composable Screen Management**: Simplify navigation logic with reusable `Screen` and `ScreenStack` components.
- **Cross-Platform Compatibility**: Designed to work with Compose Multiplatform.
- **Minimal Boilerplate**: Clean and concise API for efficient navigation.

---

## Usage

Below is an example of how to use `Navigation`:

### Define Your Screens

```kotlin
object ApplicationScreen : Screen() {

    @Composable
    override fun compose() {
        // Your screen's UI goes here
    }
}
```

### Create a Screen Stack

```kotlin
object ApplicationScreenStack : ScreenStack<Screen>(ApplicationScreen)
```

### Navigate Between Screens

```kotlin
ApplicationScreenStack.navigate(ApplicationScreen)
```

This simple API allows you to define screens and manage navigation with minimal effort.

---

## Adding to Your Project

Navigation is hosted on JitPack. To add it to your project, follow these steps:

1. Add the JitPack repository to your `settings.gradle.kts` or `build.gradle.kts`:

   ```kotlin
   repositories {
       maven { url = uri("https://jitpack.io") }
   }
   ```

2. Add the dependency to your `build.gradle.kts`:

   ```kotlin
   dependencies {
       implementation("com.github.Atlas-KT.Navigation:navigation:{version}")
   }
   ```

Replace `latest-version` with the appropriate release tag from [JitPack]().

---

## TODO Checklist

The following features are planned for future releases:

- **Cross-Platform Support**
    - [ ] Implement seamless support for iOS, Android, and Desktop platforms.
- **Package Routing**
    - [ ] Add advanced routing capabilities for organizing and managing screens in modular packages.
- **KSP Integration**
    - [ ] Introduce Kotlin Symbol Processing (KSP) for automated generation of screen-related boilerplate.

---

## Contributing

Contributions are welcome! If you’d like to report a bug, request a feature, or submit a pull request, please check the [issues](https://github.com/Atlas-KT/Navigation/issues) and [pull requests](https://github.com/Atlas-KT/Navigation/pulls) sections.

---

## License

This project is licensed under the [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0).

