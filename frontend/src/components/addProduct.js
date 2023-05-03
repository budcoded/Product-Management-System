import React, { useState } from 'react';
import { TextField, TextareaAutosize, MenuItem, Button } from '@mui/material';
import '../css/addProduct.css'
import axios from "axios";
import {useNavigate} from "react-router-dom";


const categories = ['GROCERIES', 'FRUITS','HOMEANDKITCHEN', 'FASHION','ELECTRONICS', 'SPORTS'];

function AddProduct() {
	const navigate = useNavigate();
	const [productName, setProductName] = useState('');
	const [description, setDescription] = useState('');
	const [price, setPrice] = useState('');
	const [quantity, setQuantity] = useState('');
	const [category, setCategory] = useState('');

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
			const response = await axios.post('http://localhost:1111/api/product/addProduct', {
				name: productName,
				price: price,
				quantity: quantity,
				productCategory: category,
				description: description
			});
			console.log(response.data);
			if (response.data.body === "Product Successfully Added.") {
				navigate("/admindashboard", {state: {data: response.data}});
			} else {
				alert(response.data);
			}
		} catch (error) {
			console.log(error);
			//setError(error.response);
		}
	};

	return (
		<form className="form-container" onSubmit={handleSubmit}>
			<label>Add Product</label>
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
					startAdornment: <span>Rs</span>,
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
				Add Product
			</Button>
		</form>
	);
}

export default AddProduct;
