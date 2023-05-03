import {AppBar, Button, Toolbar} from "@mui/material";
import {Link} from "react-router-dom";
import React, {useState} from 'react';
import '../css/AdminDashboard.css'
import {useLocation} from "react-router-dom";
import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import axios from "axios";

function AdminDashboard () {
	// const location = useLocation();
	// const id = location.state.data.id;
	// const name = location.state.data.name;
	// const email = location.state.data.email;
	// const mobileNumber = location.state.data.mobileNumber;
	// const role = location.state.data.role;
	return (
		<AppBar>
			<Toolbar>
				<Button component={Link} to="/addproduct" color="inherit">
					Add Product
				</Button>
				<div style={{flexGrow: 1}}/>
				<Button color="inherit" component={Link} to="/">
					Logout
				</Button>
			</Toolbar>
		</AppBar>
	);
}
export default AdminDashboard;


// function AdminDashboard() {
//     // const location = useLocation();
//     // const id = location.state.data.id;
//     // const name = location.state.data.name;
//     // const email = location.state.data.email;
//     // const mobileNumber = location.state.data.mobileNumber;
//     // const role = location.state.data.role;
//
// 	const fetchProducts = async (event) => {
// 		event.preventDefault();
// 		try {
// 			const response = await axios.get('http://localhost:1111/api/product/getProducts');
// 			console.log(response.data);
// 		} catch (error) {
// 			console.log(error);
// 		}
// 	};
//
//     return (
//         <div>
//             {/*<h2>This is admin dashboard.</h2>*/}
//             {/*<h3>Data Received: </h3>*/}
//             {/*<p>{id}, {name}, {email}, {mobileNumber}, {role}</p>*/}
//             <TableContainer component={Paper} variant="outlined">
//                 <Table aria-label="demo table">
//                     <TableHead>
//                         <TableRow>
//                             <TableCell>Dessert</TableCell>
//                             <TableCell>Calories</TableCell>
//                         </TableRow>
//                     </TableHead>
//                     <TableBody>
//                         <TableRow>
//                             <TableCell>Frozen yoghurt</TableCell>
//                             <TableCell>109</TableCell>
//                         </TableRow>
//                         <TableRow>
//                             <TableCell>Cupcake</TableCell>
//                             <TableCell>305</TableCell>
//                         </TableRow>
//                     </TableBody>
//                 </Table>
//             </TableContainer>
//         </div>
//     );
// }
// }
// export default AdminDashboard;