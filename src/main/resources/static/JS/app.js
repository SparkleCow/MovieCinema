const busquedas = [];

async function cargarPeliculas() {

    const data = {};
    data.movieName = document.getElementById("search_input").value;
    if (data.movieName === "" || data == null) {
        alert("Introduce tu busqueda");
    }
    console.log(data.movieName);

    try {
        const response = await fetch("http://localhost:8080/movies", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        const content = await response.json();

        let respuestaInformacionPelicula = "";

        console.log(content)
        let stringHTML = `<div class="img_pelicula" id="img_pelicula">
                            <img class="img_respuesta" src="${content.Poster}"/>
                        </div>
                        <div class="info_pelicula" id="info_pelicula">
                            <h1 class="titulo">${content.Title}</h1>
                            <p class="director">${content.Director}</p>
                            <p class="genero">${content.Genre}</p>
                            <p class="duracion">${content.Runtime}</p>
                            <p class="released">${content.Released}</p>
                            <p class="descripcion">${content.Plot}</p>
                        </div>`
        
        let notFoundHTML = `<div class="img_pelicula" id="img_pelicula">
                            <img class="img_respuesta" src="img/imagen1.png"/>
                        </div>
                        <div class="info_pelicula" id="info_pelicula">
                            <h1 class="titulo">Película No Encontrada</h1>
                            <p class="director">Verifica el nombre ingresado e intenta nuevamente</p>
                        </div>`


        if (content.Response === "False" ) {
            respuestaInformacionPelicula += notFoundHTML;
        }
        else{
            respuestaInformacionPelicula += stringHTML;
        }
        


        let componente = document.getElementById("respuesta_pelicula");

        componente.innerHTML = respuestaInformacionPelicula;
    } catch (error) {
        console.error('Error fetching data:', error);
    }
}




