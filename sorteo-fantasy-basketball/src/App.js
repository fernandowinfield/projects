import React from 'react';
import Landing from './views/Landing';
import Participants from './views/Participants';
import Shuffling from './views/Shuffling';
import Lottery from './views/Lottery';
import Summary from './views/Summary';
import { Container } from 'react-bootstrap';


class App extends React.Component {
  constructor(props) {
    super(props);

    this.switchView = this.switchView.bind(this);
    this.addParticipant = this.addParticipant.bind(this);
    this.shuffleParticipants = this.shuffleParticipants.bind(this);

    this.state = {
      players: [],
      shuffledPlayers: [],
      view: "landing"
    };
  }

  switchView(view) {
    this.setState({
      view
    });
  }

  addParticipant(participantName) {
    this.setState(prevState => ({
      players: [...prevState.players, participantName]
    }));
  }

  startLottery() {

  }

  shuffleParticipants() {
    if (this.state.shuffledPlayers.length === 0) {
      let nombres = [...this.state.players];
      let nombresBarajeados = [];
      let nrand, i;
      let tnom = nombres.length;
  
      for (var j = 0; j<tnom; j++) {
        nrand = Math.random();
        i = Math.floor(nrand*nombres.length);
        nombresBarajeados.push(nombres.splice(i,1)[0]);
      }
  
      this.setState({
        shuffledPlayers: [...nombresBarajeados]
      });
    }
  }


  render() {
    let viewToDisplay = <React.Fragment/>;
    switch (this.state.view) {
      case "landing":
        viewToDisplay = <Landing switchView={this.switchView}/>
        break;
      case "addParticipants":
        viewToDisplay = <Participants addParticipant={this.addParticipant}
                                      players={this.state.players}
                                      switchView={this.switchView}/>
        break;
      case "shuffling":
        viewToDisplay = <Shuffling switchView={this.switchView}
                                   shuffleParticipants={this.shuffleParticipants}/>
        break;
      case "lottery":
        viewToDisplay = <Lottery switchView={this.switchView}
                                 players={this.state.shuffledPlayers}/>
        break;
      case "summary":
        viewToDisplay = <Summary players={this.state.shuffledPlayers}/>
        break;
      default:
        viewToDisplay = <React.Fragment/>
    }

    return (
      <Container>
        {viewToDisplay}
      </Container>
    );
  }
}

export default App;
