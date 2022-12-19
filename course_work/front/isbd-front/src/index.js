import React from "react";
import ReactDOM from 'react-dom'
import 'bootstrap/dist/css/bootstrap.css'

import Example from "./components/Example";
import Example2 from "./components/Example2";
import Example3 from "./components/Example3";
import App from "./components/App"
//
// const element = <div>
//     <Example/>
//     <Example2 gluh={"глух"}/>
//     <Example3/>
// </div>;
// const ahuel = <Example3/>
// console.log(element);
ReactDOM.render(<App/>, document.getElementById('root'));