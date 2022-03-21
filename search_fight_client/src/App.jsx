import { useState } from 'react';
import axios from 'axios';
import './style/style.css';
import fighting from './asset/fighting.gif';
import Matchup from './frame/matchup';
import Scorecard from './frame/scorecard';

function App() {

  const [navigation, setNavigation] = useState('matchup');
  const [errorMessage, setErrorMessage] = useState('');
  const [searchTerms, setSearchTerms] = useState({one: '', two: ''});
  const [result, setResult] = useState({});

  const inputSearchTerms = (e) => {
    setErrorMessage('');
    setSearchTerms({
      ...searchTerms,
      [e.target.name]: e.target.value
    });
  }

  const submit = () => {
    if (searchTerms.one.trim() === '' || searchTerms.two.trim() === '' ) {
      setErrorMessage('The fields cannot be left blank.');
    } else {
      setNavigation('fighting');
      makeApiCall();
    }
  }

  const makeApiCall = () => {
    axios.get(`http://localhost:8080/api/search_terms/${searchTerms.one}/${searchTerms.two}`, {
        headers: {
          'Content-Type': 'application/json'
        }
    })
    .then((response) => {
      if (response.status === 201) {
        setResult(response.data);
        setNavigation('scorecard');
      } else {
        setErrorMessage('Sorry, there is something off with our search api\'s quota. Please come back tomorrow.');
        setNavigation('matchup');
      }
    });
  }

  const reset = () => {
    setErrorMessage('');
    setSearchTerms({});
    setNavigation('matchup');
  }

  return (
    <div className='App-container'>
      <div className='App-content'>
        {navigation === 'matchup' ? (
          <>
            <Matchup inputSearchTerms={inputSearchTerms} submit={submit} errorMessage={errorMessage}/>
          </>
        ) : navigation === 'fighting' ? (
          <div className='Center'>
            <img src={fighting} className='Fighting-gif' alt='fightingImage' />
          </div>
        ) : (
          <>
            <Scorecard reset={reset} result={result} searchTerms={searchTerms}/>
          </>
        )}
      </div>
    </div>
  );
}

export default App;