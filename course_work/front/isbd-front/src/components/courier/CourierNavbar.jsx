import React from 'react';

const CourierNavbar = (props) => {
    return (
        <div className="w-full pr-20">
            <div className="text-right h-auto pr-20">
                Logged as: {props.name}
            </div>
        </div>
    );
};

export default CourierNavbar;