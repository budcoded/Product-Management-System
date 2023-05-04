import {
	AppBar,
	Button,
	IconButton,
	Paper,
	Table,
	TableBody,
	TableCell,
	TableContainer,
	TableHead,
	TableRow,
	Toolbar
} from "@mui/material";
import { Link, useLocation } from "react-router-dom";
import {React, useEffect, useState} from "react";
import DeleteIcon from "@mui/icons-material/Delete";
import EditIcon from "@mui/icons-material/Edit";
import "../css/AdminDashboard.css";
import axios from "axios";

function AdminDashboard() {
    // const location = useLocation();
    // const id = location.state.data.id;
    // const name = location.state.data.name;
    // const email = location.state.data.email;
    // const mobileNumber = location.state.data.mobileNumber;
    // const role = location.state.data.role;
	const [products, setProducts] = useState([]);
    const fetchProducts = async () => {
        try {
            const response = await axios.get('http://localhost:1111/api/product/getProducts');
            console.log(response.data);
			setProducts(response.data);
        } catch (error) {
            console.log(error);
        }
    };

	useEffect(() => {
		fetchProducts();
	}, []);


	return (
        <>
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
			<div className="table-style">
				<TableContainer component={Paper} variant="outlined">
					<Table aria-label="products table">
						<TableHead>
							<TableRow className="bold-row">
								<TableCell className="bold-row">Name</TableCell>
								<TableCell className="bold-row">Description</TableCell>
								<TableCell className="bold-row">Category</TableCell>
								<TableCell className="bold-row">Price</TableCell>
								<TableCell className="bold-row" align="right">Actions</TableCell>
							</TableRow>
						</TableHead>
						<TableBody>
							{products.map((product) => (
								<TableRow key={product.id}>
									<TableCell className="normal-row">{product.name}</TableCell>
									<TableCell className="normal-row">{product.description}</TableCell>
									<TableCell className="normal-row">{product.productCategory}</TableCell>
									<TableCell className="normal-row">{product.price}</TableCell>
									<TableCell align="right">
										<IconButton>
											<EditIcon />
										</IconButton>
										<IconButton>
											<DeleteIcon />
										</IconButton>
									</TableCell>
								</TableRow>
							))}
						</TableBody>
					</Table>
				</TableContainer>
			</div>
		</>
    );
}

export default AdminDashboard;