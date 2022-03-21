import '../style/style.css';
import fightBelt from '../asset/fight_belt.jpg';
import reset from '../asset/reset.ico';
import OneColumnRow from './component/OneColumnRow';
import TwoColumnRow from './component/TwoColumnRow';

const Scorecard = (props) => {

    const  numberWithCommas = (number) => {
        return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    }

    return (
        <>
            <span className='Two-percent-bottom-padding'>
                <button className='Button-reset' onClick={() => props.reset()}>
                    <img src={reset} alt='reset'/>
                </button>
            </span>
            <table>
                <tbody>
                    <OneColumnRow columnClass={'Belt-scorecard'} columnValue={<img src={fightBelt} alt='fightbelt'/>}/>
                    <OneColumnRow
                        columnClass={'Bold'}
                        columnValue={
                            <header>
                                <span>{'And the winner is... '}</span>
                                <h1>{props.result.overallWinner.toUpperCase()}</h1>
                            </header>}/>
                </tbody>
            </table>
            <table>
                <tbody>
                    <TwoColumnRow
                        columnOneClass={'Column'} columnTwoClass={'Column'}
                        columnOneValue={<h2>{props.searchTerms.one.toUpperCase()}</h2>}
                        columnTwoValue={<h2>{props.searchTerms.two.toUpperCase()}</h2>}/>
                    <TwoColumnRow columnOneClass={'Bold'} columnTwoClass={'Bold'} columnOneValue={'Google hits: '} columnTwoValue={'Google hits: '}/>
                    <TwoColumnRow
                        columnOneClass={'Column'} columnTwoClass={'Column'}
                        columnOneValue={numberWithCommas(props.result.searchTermOneGoogleHits)}
                        columnTwoValue={numberWithCommas(props.result.searchTermTwoGoogleHits)}/>
                    <TwoColumnRow columnOneClass={'Bold'} columnTwoClass={'Bold'} columnOneValue={'Bing hits: '} columnTwoValue={'Bing hits: '}/>
                    <TwoColumnRow
                        columnOneClass={'Column'} columnTwoClass={'Column'}
                        columnOneValue={numberWithCommas(props.result.searchTermOneBingHits)}
                        columnTwoValue={numberWithCommas(props.result.searchTermTwoBingHits)}/>
                    <TwoColumnRow columnOneClass={'Bold'} columnTwoClass={'Bold'} columnOneValue={'Total hits: '} columnTwoValue={'Total hits: '}/>
                    <TwoColumnRow
                        columnOneClass={'Column'} columnTwoClass={'Column'}
                        columnOneValue={numberWithCommas(props.result.searchTermOneGoogleHits + props.result.searchTermOneBingHits)}
                        columnTwoValue={numberWithCommas(props.result.searchTermTwoGoogleHits + props.result.searchTermTwoBingHits)}/>
                </tbody>
            </table>
        </>
    );
    
  }
  
  export default Scorecard;