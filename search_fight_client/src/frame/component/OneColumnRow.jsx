const OneColumnRow = (props) => {

    return (
        <>
            <tr>
                <td className={props.columnClass}>
                    {props.columnValue}
                </td>
            </tr>
        </>
    );
}

export default OneColumnRow;