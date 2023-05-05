import React, { useState, useEffect } from 'react';
import { TextField, TextareaAutosize, MenuItem, Button } from '@mui/material';
import '../css/EditProduct.css'
import axios from "axios";
import {useNavigate, useParams} from "react-router-dom";
import CurrencyRupeeIcon from '@mui/icons-material/CurrencyRupee';

const categories = ['GROCERIES', 'FRUITS','HOMEANDKITCHEN', 'FASHION','ELECTRONICS', 'SPORTS' , 'BOOKS'];

function EditProduct() {
	const navigate = useNavigate();
	const [productName, setProductName] = useState('');
	const [description, setDescription] = useState('');
	const [price, setPrice] = useState('');
	const [quantity, setQuantity] = useState('');
	const [category, setCategory] = useState('');

	const {id} = useParams();

	async function fetchProduct() {
		try {
			const response = await axios.get(`http://localhost:1111/api/product/getProductById/${id}`);
			const product = response.data;
			// console.log(product);
			setProductName(product.name);
			setDescription(product.description);
			setPrice(product.price);
			setQuantity(product.quantity);
			setCategory(product.productCategory);
			console.log(productName);
			console.log(description);
			console.log(price);
			console.log(quantity);
			console.log(category);
		} catch (error) {
			console.log(error);
		}
	}

	useEffect(() => {
		// Fetch the product data from the server and populate the state
		fetchProduct();
	}, [id]);

	const handleProductNameChange = (event) => {
		setProductName(event.target.value);
	};

	const handleDescriptionChange = (event) => {
		setDescription(event.target.value);
	};

	const handlePriceChange = (event) => {
		setPrice(event.target.value);
	};

	const handleQuantityChange = (event) => {
		setQuantity(event.target.value);
	};

	const handleCategoryChange = (event) => {
		setCategory(event.target.value);
	};

	const handleSubmit = async (event) => {
		event.preventDefault();
		if (productName.trim().length === 0) {
			alert("Product Name field can't be empty.");
			return;
		}
		if (price.trim().length === 0) {
			alert("Price field can't be empty");
			return;
		}
		if (quantity.trim().length === 0) {
			alert("Quantity field can't be empty");
			return;
		}
		if (category.trim().length === 0) {
			alert("Category field can't be empty");
			return;
		}
		try {
			const response = await axios.put(`http://localhost:1111/api/product/updateProduct/${id}`, {
				name: productName,
				price: price,
				quantity: quantity,
				productCategory: category,
				description: description
			});
			console.log(response.data);
			if (response.data === "Product updated successfully.") {
				navigate("/admindashboard", {state: {data: response.data}});
			} else {
				alert(response.data);
			}
		} catch (error) {
			console.log(error);
		}
	};

	return (
		<form className="form-container" onSubmit={handleSubmit}>
			<label>Edit Product</label>
			<TextField
				label="Product Name"
				value={productName}
				onChange={handleProductNameChange}
				fullWidth
				margin="normal"
			/>

			<TextField
				label="Price"
				value={price}
				onChange={handlePriceChange}
				fullWidth
				margin="normal"
				type="number"
				InputProps={{
					startAdornment: <CurrencyRupeeIcon></CurrencyRupeeIcon>,
				}}
			/>
			<TextField
				label="Quantity"
				value={quantity}
				onChange={handleQuantityChange}
				fullWidth
				margin="normal"
				type="number"
			/>
			<TextField
				select
				label="Category"
				value={category}
				onChange={handleCategoryChange}
				fullWidth
				margin="normal"
			>
				{categories.map((option) => (
					<MenuItem key={option} value={option}>
						{option}
					</MenuItem>
				))}
			</TextField>

			<TextareaAutosize
				className="MuiTextareaAutosize-root"
				label="Description"
				value={description}
				onChange={handleDescriptionChange}
				fullWidth
				margin="normal"
				minRows={5}
				maxRows={10}
				placeholder="Enter a description"
			/>
			<Button variant="contained" color="primary" type="submit">
				Edit Product
			</Button>
		</form>
	);
}

export default EditProduct;
