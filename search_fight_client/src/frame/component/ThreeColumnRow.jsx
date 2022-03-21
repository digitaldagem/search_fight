const ThreeColumnRow = (props) => {

    return (
        <>
            <tr>
                <td className={props.columnOneClass}>
                    {props.columnOneValue}
                </td>
                <td className={props.columnTwoClass}>
                    {props.columnTwoValue}
                </td>
                <td className={props.columnThreeClass}>
                    {props.columnThreeValue}
                </td>
            </tr>
        </>
    );
}

export default ThreeColumnRow;