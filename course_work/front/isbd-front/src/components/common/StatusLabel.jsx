import React from 'react';
import PropTypes from 'prop-types';

const StatusLabel = props => {

    function getStatusClass(status) {
        let commonPart = 'w-100 badge';

        switch (status) {
            case 'WAITING': {
                break;
            }
            case 'DELIVERING': {
                commonPart += ' badge-primary';
                break;
            }
            case 'DELIVERED': {
                commonPart += ' badge-success';
                break;
            }
            case 'LOST': {
                commonPart += ' badge-secondary';
                break;
            }
            case 'DESTROYED': {
                commonPart += ' badge-danger';
                break;
            }
            default:
                break;
        }
        return commonPart;
    }

    return (
        <span className={getStatusClass(props.status)}>{props.status}</span>
    );
};

StatusLabel.propTypes = {};

export default StatusLabel;