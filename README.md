
![Splash](https://raw.githubusercontent.com/lecasme/RappiTV/master/app/screenshots/logo.png?raw=true)

MVVM Android Pattern + Live Data + View Binding + Data Binding + Koin + Room + Retrofit 

## Getting Started

TMDB es un app de busqueda de peliculas usando las API's de [themoviedb](https://developers.themoviedb.org/)


Referencias a las librerias usadas en este proyecto:

- [Injection: koin](https://insert-koin.io/)
- [Remote API: retrofit](https://square.github.io/retrofit/)
- [Persistence: room](https://developer.android.com/training/data-storage/room)
- [Image: glide](https://github.com/bumptech/glide)
- [Video: YoutubePlayer](https://developers.google.com/youtube/android/player?hl=es)

## Features Incluidos

- Pantalla de Splash

Llamada al [API](https://api.themoviedb.org/3/list/1/) que devuelve la lista de peliculas relacionada con los comics de Marvel posteriomente la lista se guarda en la base local del dispositivo.

- Pantalla de Home

Muestra en vista de listado de 3 columnas las peliculas previamente descargadas, puede ser ordenadas por popularidad o calificacion con el interrupor superior derecha, ademas puede ser filtrada por nombre de pelicula.

- Pantalla de Landing

Muestra todo el detalle de la pelicula seleccionada, asi como que el trailer que se obtiene mediante otra llamada al [API](https://api.themoviedb.org/3/movie/100402/videos)


## Screenshots

![Home](https://raw.githubusercontent.com/lecasme/RappiTV/master/app/screenshots/home.png?raw=true)
![Landing](https://raw.githubusercontent.com/lecasme/RappiTV/master/app/screenshots/landing.png?raw=true)


