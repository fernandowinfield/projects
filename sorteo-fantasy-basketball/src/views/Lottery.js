import React from 'react';
import Button from 'react-bootstrap/Button';
import Spinner from 'react-bootstrap/Spinner';
import TitleJumbotron from '../components/TitleJumbotron';

const WAIT = 2500;

class Lottery extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      players: this.props.players,
      currentPlayer: -1,
      showPlayer: false,
      loading: false,
      startedShowing: false
    }

    this.currentPlayer = -1;
    this.loading = false;
  }

  handleParticipantNameText(name) {
    this.setState({
      participantName: name
    });
  }

  handleKeyDown(keyPressed, props) {
    if (keyPressed === "Enter") {
      // Do nothing for now
    }
  }

  seeResults(props) {
    // Stopping condition
    if (this.state.currentPlayer === this.state.players.length) {
      props.switchView("summary");
    }
    else if (this.state.showPlayer) {
      setTimeout(() => {
        this.setState({
          loading: true,
          showPlayer: false
        });
      },
      WAIT);
    }
    else if (this.state.loading) {
      setTimeout(() => {
        this.setState(prevState => ({
          loading: false,
          showPlayer: true,
          currentPlayer: prevState.currentPlayer + 1
        }));
      },
      WAIT);
    }
    else {
      this.setState({
        loading: true,
        startedShowing: true
      });
    }
    // else if (!this.state.loading) {
    //   this.setState({
    //     loading: true
    //   });
    // }

    // for (let i=0; i<this.state.players.length; i++) {
    //   this.setState({
    //     loading: true
    //   });
    //   setTimeout(() => {
    //     this.setState({
    //       loading: false,
    //       currentPlayer: i
    //     });
    //   },
    //   2000);
    // }
  }

  componentDidUpdate() {
    if (this.state.startedShowing) {
      this.seeResults(this.props);
    }
  }

  // TODO: replace arrow with icon
  render() {
    const resultsButton = this.state.currentPlayer === -1 ?
                            <Button variant="outline-secondary"
                                    onClick={() => this.seeResults(this.props)}>
                              Ver resultados
                            </Button> :
                            <React.Fragment/>;
    const loadingSpinner = this.state.loading ?
                             <Spinner animation="border"
                                      variant="secondary"
                                      className="lotterySpinner"/> :
                             <React.Fragment/>;
    const onTheBlock = this.state.showPlayer ?
                         <React.Fragment>
                           <h2 className="blackFont">{`${this.state.players.length-this.state.currentPlayer}`}</h2>
                           <h3>{`${this.state.players[this.state.currentPlayer]}`}</h3>
                         </React.Fragment> :
                         <React.Fragment/>;

    return (
      <div className="customContainer">
        <TitleJumbotron/>
        {resultsButton}
        {loadingSpinner}
        {onTheBlock}
      </div>
    );
  }
}
export default Lottery;