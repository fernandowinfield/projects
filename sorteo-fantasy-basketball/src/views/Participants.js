import React from 'react';
import Button from 'react-bootstrap/Button';
import TitleJumbotron from '../components/TitleJumbotron';
import ParticipantsList from '../components/ParticipantsList';
import TextField from '@material-ui/core/TextField';
import { createMuiTheme, ThemeProvider } from '@material-ui/core/styles';

const theme = createMuiTheme({
  palette: {
    primary: {
      light: '#6c757d',
      main: '#6c757d',
      dark: '#6c757d',
      contrastText: '#fff',
    },
    secondary: {
      light: '#ff7961',
      main: '#f44336',
      dark: '#ba000d',
      contrastText: '#000',
    },
  },
});

class Participants extends React.Component {
  constructor(props) {
    super(props);

    this.handleParticipantNameText = this.handleParticipantNameText.bind(this);

    this.state = {
      participantName: ""
    }
  }

  handleParticipantNameText(name) {
    this.setState({
      participantName: name
    });
  }

  handleKeyDown(keyPressed, props) {
    if (keyPressed === "Enter") {
      props.addParticipant(this.state.participantName);
      this.handleParticipantNameText("");
    }
  }

  handleAddButton(props) {
    props.addParticipant(this.state.participantName);
    this.handleParticipantNameText("");
  }

  // TODO: replace arrow with icon
  render() {
    const name = this.state.participantName;
    return (
      <div className="customContainer">
        <ThemeProvider theme={theme}>
          <TitleJumbotron/>
          <p className="instructionsText">
          ↓ Agrega los jugadores
          </p>
          <hr className="instructionsHr"/>
          <TextField id="outlined-basic"
                    label="Nombre del jugador"
                    variant="outlined"
                    onChange={(event) => this.handleParticipantNameText(event.target.value)}
                    onKeyDown={(event) => this.handleKeyDown(event.key, this.props)}
                    value={name}/>
          <Button variant="outline-secondary addButton borderlessButton"
                  onClick={() => this.handleAddButton(this.props)}>
            +
          </Button>
          <ParticipantsList players={this.props.players}
                            switchView={this.props.switchView}/>
        </ThemeProvider>
      </div>
    );
  }
}
export default Participants;