import React from 'react';
import StatusLabel from "../common/StatusLabel";

const CustomerGood = props => {

    return (
        <React.Fragment>
            <div className="card h-100">
                <h2 className="card-title">
                    <StatusLabel status={props.good_status}></StatusLabel>
                </h2>
                <p>
                    {props.description}
                </p>
            </div>
        </React.Fragment>
    );
};

export default CustomerGood;