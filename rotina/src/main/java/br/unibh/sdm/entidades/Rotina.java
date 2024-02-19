package br.unibh.sdm.entidades;

import java.util.Date;
import java.util.Objects;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "rotina")
public class Rotina {

    @DynamoDBAutoGeneratedKey
    @DynamoDBHashKey
    private String id;
    @DynamoDBAttribute
    private String hora;
    @DynamoDBAttribute
    private String diaDaSemana;
    @DynamoDBAttribute
    private Date data;


    @Override
    public String toString() {
        return "Rotina [id=" + id + ", hora=" + hora + ", diaDaSemana=" + diaDaSemana + ", data=" + data + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hora, diaDaSemana, data);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Rotina other = (Rotina) obj;
        return Objects.equals(id, other.id) &&
                Objects.equals(hora, other.hora) &&
                Objects.equals(diaDaSemana, other.diaDaSemana) &&
                Objects.equals(data, other.data);
    }

}
