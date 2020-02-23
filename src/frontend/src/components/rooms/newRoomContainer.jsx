import React from "react";
import connect from "react-redux/es/connect/connect";

function NewRoomContainer(props) {

    return (
        <>
            <div>New room container</div>
        </>
    );
}

const mapStateToProps = (state) => {
    return {
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(NewRoomContainer);