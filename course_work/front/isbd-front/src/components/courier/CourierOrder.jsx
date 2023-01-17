import React from 'react';
import StatusLabel from "../common/StatusLabel";
import CourierGood from "./CourierGood";
import StatusButtons from "./StatusButtons";
// import CourierGood
const CourierOrder = props => {
    return (
        <React.Fragment>
            <div className="card">
                <div className="card-title">
                    <StatusLabel status={props.order_status}></StatusLabel>
                </div>
                <div className="d-flex w-550 justify-content-between">
                    <div>
                        <div className="card-title">
                        </div>
                        <p className="text-left">
                            Departure: {props.departure}
                        </p>
                        <p className="text-left">
                            Destination: {props.destination}
                        </p>
                        <p className="text-left">
                            Description:
                        </p>
                        <p className="text-left">
                            {props.description}
                        </p>
                    </div>

                    <div className="align-self-start">
                        <StatusButtons status={props.order_status} order_id={props.order_id} mode="order"/>
                    </div>
                </div>
                <div>
                    <details
                        className="collapse-panel w-550 mw-full">
                        <summary className="collapse-header">
                            Order content
                        </summary>
                        <div className="collapse-content">
                            <CourierGood good_status={'WAITING'} description={'Hello'}></CourierGood>
                            <CourierGood good_status={'DELIVERED'} description={'Hell yeah'}></CourierGood>
                            <CourierGood good_status={'DELIVERING'} description={'Hell yeah'}></CourierGood>
                            <CourierGood good_status={'DESTROYED'} description={'Hell yeah'}></CourierGood>
                            <CourierGood good_status={'LOST'} description={'Blyat'}></CourierGood>
                        </div>
                    </details>
                </div>
            </div>
        </React.Fragment>
    );
};

CourierOrder.propTypes = {};

export default CourierOrder;