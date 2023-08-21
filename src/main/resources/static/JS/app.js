async function cargarPeliculas(){

    const data={};
    data.busqueda = document.getElementById("search_input").value;
    if(data.busqueda==="" || data==null){
        alert("Introduce tu busqueda <3");
    }

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

    for(let i of content){
        let stringHTML = `<div class="img_pelicula" id="img_pelicula">
                            <img class="img_respuesta" src="${i.Poster}"/>
                          </div>
                          <div class="info_pelicula" id="info_pelicula">
                            <h1 class="titulo">${i.Title}</h1>
                            <p class="director">${i.Director}</p>
                            <p class="genero">${i.Genre}</p>
                            <p class="duracion">${i.Runtime}</p>
                            <p class="released">${i.Released}</p>
                            <p class="descripcion">${i.Plot}</p>
                          </div>`

        respuestaInformacionPelicula+=stringHTML;
    }
    
    let componente = document.getElementById("respuesta_pelicula");

    componente.innerHTML = respuestaInformacionPelicula;
}

function prueba(){
    let stringHTML = `<div class="img_pelicula" id="img_pelicula">
                            <img class="img_respuesta" src="img/interstellar.jpg"/>
                          </div>
                          <div class="info_pelicula" id="info_pelicula">
                            <h1 class="titulo">Amor mio</h1>
                            <p class="director">Guillermo totoro</p>
                            <p class="genero">Accion</p>
                            <p class="duracion">2 horas perros</p>
                            <p class="released">2023</p>
                            <p class="descripcion">Un perro, nada mas que decir.</p>
                          </div>`
    let componente = document.getElementById("respuesta_pelicula");

    componente.innerHTML = stringHTML;
}
