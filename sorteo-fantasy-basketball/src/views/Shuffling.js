import { React, useEffect } from 'react';
import Backdrop from '@material-ui/core/Backdrop';
import CircularProgress from '@material-ui/core/CircularProgress';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
  backdrop: {
    zIndex: theme.zIndex.drawer + 1,
    color: '#fff',
  },
}));

const SimpleBackdrop = (props) => {
  const classes = useStyles();

  useEffect(() => {
    props.shuffleParticipants();
    setTimeout(() => {
      props.switchView("lottery");
    },
    3500);
  });

  return (
    <div>
      <Backdrop className={classes.backdrop} open={true}>
        <CircularProgress color="inherit" />
        <h6 className="backdropText">
          Barajeando para que el sorteo no esté arreglado
        </h6>
      </Backdrop>
    </div>
  );
}
export default SimpleBackdrop;