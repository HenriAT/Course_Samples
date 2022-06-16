import React from "react";
import { TitleBar } from "./general";
import { PokemonSelector } from "./pokemonselector.js";
import { PokemonCard } from "./pokemoncard.js";

export class PokemonContainerComponent extends React.Component {
  constructor(props) {
    super(props);
    // console.log(JSON.stringify(props));
    this.state = { pokemons: [], pokemon: {}, description: {} };
    this.getPokemon = this.getPokemon.bind(this);
  }

  componentDidMount() {
    fetch("https://pokeapi.co/api/v2/pokemon?offset=0&limit=151")
      .then((response) => response.json())
      .then((data) => {
        this.setState({ pokemons: data.results });
        this.getPokemon(data.results[0].url);
      });
  }

  getPokemon(url) {
    // console.log(url);
    fetch(url)
      .then((response) => response.json())
      .then((data) => {
        this.setState({ pokemon: data });
        fetch(data.species.url)
          .then((response) => response.json())
          .then((data) => {
            this.setState({ description: data });
            // console.log(data);
          });
      });
  }

  render() {
    if (
      Object.keys(this.state.pokemon).length === 0 ||
      Object.keys(this.state.description).length === 0
    )
      return null;
    return (
      <div>
        <TitleBar title="First Generation of Pokémon" />
        <PokemonSelector
          pokemons={this.state.pokemons}
          callback={this.getPokemon}
        />
        <PokemonCard
          pokemon={this.state.pokemon}
          pokemons={this.state.pokemons}
          description={this.state.description}
          callback={this.getPokemon}
        />
      </div>
    );
  }
}
export default PokemonContainerComponent;
