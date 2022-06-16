import React from "react";
import { capitalizeFirstLetter } from "../generalfunctions.js";

export const Pokemon = ({ pokemon }) => {
  return (
    <option value={pokemon.url}>{capitalizeFirstLetter(pokemon.name)}</option>
  );
};

const PokemonList = ({ pokemons }) => {
  return pokemons.map((pokemon) => (
    <Pokemon pokemon={pokemon} key={pokemon.name} />
  ));
};

export const PokemonSelector = ({ pokemons, callback }) => {
  const pokemonOptions = PokemonList({ pokemons });

  function handleChange() {
    const url = document.getElementById("select").value;
    callback(url);
  }

  return (
    <div>
      <select id="select" className="form-select" onChange={handleChange}>
        {pokemonOptions}
      </select>
    </div>
  );
};
