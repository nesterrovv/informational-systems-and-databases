import React from 'react';
import StatusLabel from "../common/StatusLabel";

const CustomerGood = props => {

    return (
        <React.Fragment>
            <div className="card h-100">
                <StatusLabel status={props.good_status}></StatusLabel>
                <p>
                    {props.description}
                </p>
            </div>
        </React.Fragment>
    );
};

export default CustomerGood;