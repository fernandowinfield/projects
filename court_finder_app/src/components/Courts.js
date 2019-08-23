import React from 'react';
import {connect} from "react-redux";
import Court from "./Court";


const mapStateToProps = (state) => {
  const courts = state.courts;
  const courtsMap = state.courtsMap;
  return {courts, courtsMap};
}

const Courts = (props) => {
  const courts = props.courts.map((court,i) =>
    <Court key={i} info={court} playersNum={props.courtsMap[court.id] !== undefined ? props.courtsMap[court.id].length : 0}/>
  );

  return (
    <React.Fragment>
      {courts}
    </React.Fragment>
  );
}
export default connect(mapStateToProps) (Courts);