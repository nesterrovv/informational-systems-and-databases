import React from 'react';
import StatusLabel from "../common/StatusLabel";

const CustomerGood = props => {

    return (
        <React.Fragment>
            <div className="card p-10">
                <StatusLabel status={props.good_status}></StatusLabel>
                <span className="badge mt-5 w-25">{props.dimensions}</span>
                <p>
                    {props.description}
                </p>
            </div>
        </React.Fragment>
    );
};

export default CustomerGood;