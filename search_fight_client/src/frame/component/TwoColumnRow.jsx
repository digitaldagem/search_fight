const TwoColumnRow = (props) => {

    return (
        <>
            <tr>
                <td className={props.columnOneClass}>
                    {props.columnOneValue}
                </td>
                <td className={props.columnTwoClass}>
                    {props.columnTwoValue}
                </td>
            </tr>
        </>
    );
}

export default TwoColumnRow;