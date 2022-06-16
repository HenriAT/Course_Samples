import React from "react";
import { capitalizeFirstLetter } from "../generalfunctions.js";

export const PokemonCard = ({ pokemon, pokemons, description, callback }) => {
  function handleButtonPressed() {
    const url = pokemons[pokemon.id % 151].url;
    document.getElementById("select").value = url;
    callback(url);
  }

  return (
    <div className="card">
      <img
        className="card-img-top"
        src={pokemon.sprites.other["official-artwork"].front_default}
        alt="Selected Pokémon"
      />
      <div className="card-body">
        <h5 className="card-title">
          {capitalizeFirstLetter(pokemon.name) +
            " #" +
            pokemon.id.toString().padStart(3, 0)}
        </h5>
        <p className="card-text">
          {filterEnglish(description.flavor_text_entries).flavor_text.replace(
            "\f",
            " "
          )}
        </p>
        <button
          type="button"
          className="btn btn-primary"
          onClick={handleButtonPressed}
        >
          Next Pokémon
        </button>
      </div>
    </div>
  );
};

function filterEnglish(entries) {
  const result = entries.filter(
    (entry) => entry.version.name === "red" && entry.language.name === "en"
  );
  return result[0];
}
