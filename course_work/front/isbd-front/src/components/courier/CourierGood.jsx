import React from 'react';
import StatusLabel from "../common/StatusLabel";
import StatusButtons from "./StatusButtons";

const CourierGood = props => {
    return (
        <React.Fragment>
            <div className="card">
                <div className="d-flex align-items-center justify-content-between align-items-start">
                    <div className="align-self-start">
                        <p>
                            {props.description}
                        </p>
                        <StatusLabel status={props.good_status}></StatusLabel>
                    </div>
                    <div className="align-self-start">
                        <StatusButtons status={props.good_status} mode="good"/>
                    </div>
                </div>
            </div>
        </React.Fragment>
    );
};


export default CourierGood;