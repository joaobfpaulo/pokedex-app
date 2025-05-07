# Pokédex Android App

A modern Pokédex Android application built with Kotlin and powered by clean architecture principles. The app showcases Pokémon data using the latest Android development tools such as Jetpack Compose, Coroutines, Flows, Room, Hilt, and more.

## ✨ Features

- 🔍 Browse and search Pokémon with detailed stats
- 🖼️ Lazy-loaded images using Coil
- 🧠 Clean architecture with a separation of concerns
- 💾 Local caching with Room database
- 🔌 Remote data from PokéAPI using Retrofit
- 📦 Dependency injection with Hilt
- 🧭 Jetpack Compose for reactive UI
- 🔁 Kotlin Coroutines and Flows for async & reactive programming
- 🪵 Timber for advanced logging

---

## 🛠️ Tech Stack

| Tool/Library     | Purpose                                      |
|------------------|----------------------------------------------|
| **[Kotlin](https://kotlinlang.org/)**       | Programming language                         |
| **[Jetpack Compose](https://developer.android.com/compose)** | UI toolkit for declarative UI             |
| **[Retrofit](https://square.github.io/retrofit/)**     | Network requests                             |
| **[Room](https://developer.android.com/training/data-storage/room)**         | Local database caching                       |
| **[Hilt](https://dagger.dev/hilt/)**         | Dependency Injection                         |
| **[Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)**   | Asynchronous programming                     |
| **[Flow](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/)**         | Reactive stream data handling                |
| **[Coil](https://coil-kt.github.io/coil/)**         | Image loading library                        |
| **[Timber](https://github.com/JakeWharton/timber)**       | Logging utility                              |

---

## 📸 Screenshots

<img src="https://github.com/user-attachments/assets/bd9a4175-2778-4a0b-ac95-a3b67cf5351f" width="200" height="400" />
<img src="https://github.com/user-attachments/assets/a0049c57-add1-4650-a06c-59adf94fd62a" width="200" height="400" />
<img src="https://github.com/user-attachments/assets/85045ddb-457a-451c-823a-cc7078135679" width="200" height="400" />

---

## 🧩 Architecture

This app follows a layered clean architecture:

**Presentation** (Jetpack Compose UI, Coil) &rarr; **Domain** (UseCases, business logic) &rarr; **Data** (Repository implementation, Room, Retrofit)

---

## 🔐 API
This app uses the [PokéAPI](https://pokeapi.co/). No API key is required.

---

## 📄 License
This project is licensed under the MIT License. See the `LICENSE` file for details.

---

## 🤝 Contributing
Contributions are welcome! Please open issues and pull requests as needed.

---

## 🙌 Acknowledgements
- PokéAPI
- Android Dev Community
