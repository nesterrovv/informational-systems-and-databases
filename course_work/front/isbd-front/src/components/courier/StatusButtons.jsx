import React from 'react';
import axios from "axios";

const StatusButtons = (props) => {

    //TODO: write logic whent end-point is ready
    function pickUp() {

    }

    function deliver() {

    }

    function lost() {

    }

    function destroy() {

    }
    function pickButton(props) {
        return (
            <button className="btn btn-primary w-100" type="button" onClick={pickUp}>Pick Up</button>
        );
    }

    function deliverButton(props) {
        return (
            <button className="btn btn-success w-100" type="button" onClick={deliver}>Delivered</button>
        );
    }

    function lostButton(props) {
        return (
            <button className="btn btn-secondary w-100" type="button" onClick={lost}>Lost</button>
        );
    }

    function markAsDestroyedButton(props) {
        return (
            <button className="btn btn-danger w-100" type="button" onClick={destroy}>
                Destroyed
            </button>
        );
    }

    switch (props.status) {
        case "WAITING": {
            return pickButton();
        }
        case 'DELIVERING': {
            return (
                <div className="d-flex align-items-center flex-column">
                    <div className="mb-10">
                        {deliverButton()}
                    </div>
                    <div className="mb-10">
                        {lostButton()}
                    </div>
                    <div className="mb-10">
                        {markAsDestroyedButton()}
                    </div>
                </div>
            );
        }
        case 'DELIVERED': {
            break;
        }
        case 'LOST': {
            return (
                <div className="d-flex align-items-center flex-column">
                    <div className="mb-10">
                        {pickButton()}
                    </div>
                    {markAsDestroyedButton()}
                </div>
            );
        }
        case 'DESTROYED': {
            break;
        }
        default:
            break;
    }
};

export default StatusButtons;