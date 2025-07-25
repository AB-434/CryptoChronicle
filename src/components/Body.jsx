import React from 'react'
import ketan from '../assets/ketan.jpg';
import harry from '../assets/harry.jpg';
import arnaw from '../assets/arnaw.jpeg';
import jagruthi from '../assets/jag.jpg';
import Cards from './Cards';
import { useNavigate } from 'react-router-dom';


export default function Body() {

    const navigate = useNavigate();
    const handleClick = () => {
        navigate('/practice');
    };

    return (
        <>
            <div className='middle-body'>
                <div>
                    <h2>Welcome to CipherChronicle</h2>
                    <p>
                        Your gateway to the fascinating world of historical cryptography.
                        From Caesar's secret messages to Vigenère's unbreakable codes, this tool lets you explore, 
                        learn, and practice the classical ciphers that once protected empires and revolutions.
                    </p>
                    <button onClick={handleClick}>Let's go</button>
                
                </div>
            </div>
        <div className='who-we-are'>
            <div className="section-title">
                <h2>Meet Our Team</h2>
                <p >Dedicated individuals working to make a difference</p>
            </div>
            <div className="testimonials-container">
                <Cards img={ketan} heading={"Ketan Suthar"} role={"Frontend Developer"}/>
                <Cards img={harry} heading={"Harry Mistry"} role={"Frontend Developer"}/>
                <Cards img={arnaw} heading={"Arnaw Baitha"} role={"Backend Developer"}/>
                <Cards img={jagruthi} heading={"Jagruthi Boyapati"} role={"Backend Developer"}/>
            </div>
        </div>
        
        </>
    )
}
