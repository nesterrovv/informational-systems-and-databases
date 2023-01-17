import React from 'react';
import CustomerGood from "./CustomerGood";
import StatusLabel from "../common/StatusLabel";

const CustomerOrder = props => {
    return (
        <React.Fragment>

            <div className="card">
                <div className="card-title">
                    <StatusLabel status={props.order_status}></StatusLabel>
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
                <div>
                    <details
                        className="collapse-panel w-400 mw-full">
                        <summary className="collapse-header">
                            Order content
                        </summary>
                        <div className="collapse-content">
                            {props.goods.map(
                                good => (
                                    <CustomerGood good_status={good.status} description={good.description} dimensions={good.dimensions}></CustomerGood>
                                )
                            )
                            }
                            {/*<CustomerGood good_status={'WAITING'} description={'Hello'}></CustomerGood>*/}
                            {/*<CustomerGood good_status={'DELIVERED'} description={'Hell yeah'}></CustomerGood>*/}
                            {/*<CustomerGood good_status={'LOST'} description={'Blyat'}></CustomerGood>*/}
                        </div>
                    </details>
                </div>
            </div>
        </React.Fragment>
    );
};

CustomerOrder.propTypes = {};

export default CustomerOrder;