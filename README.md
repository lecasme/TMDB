
![Splash](https://raw.githubusercontent.com/lecasme/RappiTV/master/app/screenshots/logo.png?raw=true)

MVVM Android Pattern + Live Data + View Binding + Data Binding + Koin + Room + Retrofit 

## Getting Started

RappiTV es un app de busqueda de peliculas usando las API's de [themoviedb](https://developers.themoviedb.org/)


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


## Mejoras Faltantes

- El splash no deberia llamar siempre al [API](https://api.themoviedb.org/3/list/1/) de peliculas, se debe guardar en las preferencias la ultima actualizacion, para poder validar que no se llama al servicio tan seguido, sino que tenga un tiempo de espera.
- Verificar el estado de la conexion mediante un broadcast, de ese modo al momento de recuperar la conexion si no se tienen peliculas cargadas, las cargara inmediantamente
- Implementar navigation de android Jetpack creando el graph que tiene las rutas.
- Añadir pantalla de favoritos usando las apis de [themoviedb](https://developers.themoviedb.org/), se debe implementar el post que guarda la pelicula y el get de los favoritos del usuario siguiendo el patron.
- Mejorar un poco la transicion quizas es demasiado rapida.
- Test Unitarios mas complejos o mejor enfocados.


## Deuda Tecnica

- Cambiar las dimensiones usadas al archivo de dimens
- Algunos iconos añadidos no estan redimensionados o podrian convertise a vectores
- Algunas keys o ids (usadas en transiciones, extras de los intents) estan en duro, deben pasar a constantes
- Posibilidad de haber dejado algun texto fuera del archivo strings
- El nombre de los colores en el archivo colors no es el indicado
- El color del RatingBar cuando no esta activo es muy oscuro

## Screenshots

![Splash](https://raw.githubusercontent.com/lecasme/RappiTV/master/app/screenshots/splash.jpg?raw=true)
![Offline](https://raw.githubusercontent.com/lecasme/RappiTV/master/app/screenshots/offline.jpg?raw=true)
![Home](https://raw.githubusercontent.com/lecasme/RappiTV/master/app/screenshots/home.jpg?raw=true)
![Sorted](https://raw.githubusercontent.com/lecasme/RappiTV/master/app/screenshots/sorted.jpg?raw=true)
![Filtered](https://raw.githubusercontent.com/lecasme/RappiTV/master/app/screenshots/filtered.jpg?raw=true)
![Landing](https://raw.githubusercontent.com/lecasme/RappiTV/master/app/screenshots/landing.jpg?raw=true)


