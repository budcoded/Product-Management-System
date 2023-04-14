import React, { useState } from 'react';
import axios from 'axios';
import './login.css';

function LoginPage() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post('/api/login', {
        email: email,
        password: password,
      });
      console.log(response.data);
    } catch (error) {
      setError(error.response.data.message);
    }
  };

  return (
    <div>
      <h1 className="h1"> Login Page </h1>
      <form className = "login-form" onSubmit={handleSubmit}>
        <div>
          <label className = "login-form label" htmlFor="email">Email:</label>
          <input className='login-form input'
            type="email"
            id="email"
            value={email}
            onChange={handleEmailChange}
            required
          />
        </div>
        <div>
          <label className = "login-form label" htmlFor="password">Password:</label>
          <input className='login-form input'
            type="password"
            id="password"
            value={password}
            onChange={handlePasswordChange}
            required
          />
        </div>
        <button className = "login-form button" type="submit">Login</button>
      </form>
      { error && <p>{error}</p>}
    </div>
  );
}

export default LoginPage;
