import '../style/style.css';
import OneColumnRow from './component/OneColumnRow';
import ThreeColumnRow from './component/ThreeColumnRow';

const Matchup = (props) => {

  return (
    <>
      <table className='Five-percent-top-bottom-padding'>
          <tbody>
            <OneColumnRow columnClass={''} columnValue={<h1>{'SEARCH FIGHT'}</h1>}/>
          </tbody>
      </table>
      <table>
          <tbody>
            <ThreeColumnRow 
              columnOneClass={'Column'} columnTwoClass={'Bold Versus-column'} columnThreeClass={'Column'}
              columnOneValue={<input className='Input' type='text' onChange={(e) => props.inputSearchTerms(e)} name='one'/>}
              columnTwoValue={<h1>{' vs '}</h1>}
              columnThreeValue={<input className='Input' type='text' onChange={(e) => props.inputSearchTerms(e)} name='two'/>}/>
          </tbody>
      </table>
      <h4 className='Error-text'>{props.errorMessage}</h4>
      <table>
          <tbody>
            <OneColumnRow columnClass={''} columnValue={<button className='Button-fight' onClick={() => props.submit()}>Fight</button>}/>
          </tbody>
      </table>
    </>
  );
  
}

export default Matchup;